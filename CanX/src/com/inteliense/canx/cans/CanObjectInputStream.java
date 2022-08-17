package com.inteliense.canx.cans;

import java.util.jar.JarInputStream;

public class CanObjectInputStream extends JarInputStream {

    private Can can;

    public CanObjectInputStream(CanInputStream is) throws Exception {
        super(is);
        can = is.getObj();
    }

}
