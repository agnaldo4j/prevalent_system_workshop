package com.agnaldo4j.system.prevalent;

import java.util.List;

public interface Query<RETURN, DOMAIN> {
    RETURN execute(List<DOMAIN> state);
}
