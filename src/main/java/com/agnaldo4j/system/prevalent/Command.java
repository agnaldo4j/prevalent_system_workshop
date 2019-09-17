package com.agnaldo4j.system.prevalent;

import java.io.Serializable;
import java.util.List;

public interface Command<DOMAIN> extends Serializable {
    public void execute(List<DOMAIN> state);
}
