package com.agnaldo4j.system.prevalent;

import org.apache.commons.io.FileUtils;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class PrevalentSystemWorkshop<DOMAIN> implements PrevalentSystem<DOMAIN> {

    private List<DOMAIN> state;
    private ObjectOutputStream journal;
    private File stateFile;

    public PrevalentSystemWorkshop() {
        this("simulator_system.dat");
    }

    public PrevalentSystemWorkshop(String stateFile) {
        this(new File(stateFile));
    }

    public PrevalentSystemWorkshop(File stateFile) {
        this.stateFile = stateFile;
    }

    public void destroyState() throws IOException {
        journal.flush();
        journal.close();
        this.stateFile.delete();
    }

    public synchronized void execute(Command command) throws IOException {
        writeToJournal(command);
        command.execute(state);
    }

    public <RESULT> RESULT execute(Query<RESULT, DOMAIN> query) {
        return query.execute(state);
    }

    public void load(List<DOMAIN> initialState) throws IOException, ClassNotFoundException {
        state = restoreState(initialState);
        if(this.stateFile.exists()) {
            File backupFile = new File(this.stateFile.getName() + ".bkp");
            if (backupFile.exists()) backupFile.delete();
            FileUtils.copyFile(this.stateFile, backupFile);
        }
        journal = new ObjectOutputStream(new FileOutputStream(this.stateFile));
        writeToJournal(state);
    }

    private List<DOMAIN> restoreState(List<DOMAIN> initialState) throws IOException, ClassNotFoundException {
        if (this.stateFile.exists()) return restoreStateFrom(this.stateFile);
        else return initialState;
    }

    private List<DOMAIN> restoreStateFrom(File storage) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(storage);
        ObjectInputStream input = new ObjectInputStream(fileInputStream);
        List<DOMAIN> restoredState = restoreImage(input);
        restoreCommands(restoredState, input);
        input.close();
        fileInputStream.close();
        return restoredState;
    }

    private List<DOMAIN> restoreImage(ObjectInputStream input) throws IOException, ClassNotFoundException {
        return (List<DOMAIN>) input.readObject();
    }

    private void restoreCommands(List<DOMAIN> state, ObjectInputStream input) throws IOException, ClassNotFoundException {
        try {
            while (true) ((Command) input.readObject()).execute(state);
        } catch (EOFException e) { }
    }

    private void writeToJournal(Object object) throws IOException {
        journal.writeObject(object);
        journal.flush();
    }
}
