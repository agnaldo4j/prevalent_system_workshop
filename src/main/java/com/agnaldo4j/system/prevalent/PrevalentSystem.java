package com.agnaldo4j.system.prevalent;

import java.io.IOException;
import java.util.List;

public interface PrevalentSystem<DOMAIN> {
    public void destroyState() throws IOException;

    public void execute(Command command) throws IOException;

    public <T> T execute(Query<T, DOMAIN> query);

    public void load(List<DOMAIN> initialState) throws IOException, ClassNotFoundException;
}

