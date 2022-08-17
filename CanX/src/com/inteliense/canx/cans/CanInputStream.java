package com.inteliense.canx.cans;

import com.inteliense.canx.Startup;
import com.inteliense.canx.utils.AES;
import com.inteliense.canx.utils.SHA;

import java.io.*;

public class CanInputStream extends InputStream {

    private ByteArrayInputStream bis;
    private byte[] data;
    private Can obj;

    public CanInputStream(String path) throws Exception {

        bis = new ByteArrayInputStream(new byte[]{});
        FileInputStream in = new FileInputStream(path);
        BufferedInputStream bin = new BufferedInputStream(in);
        ByteArrayOutputStream ba = new ByteArrayOutputStream();

        byte[] bite = new byte[1];
        while(bin.read(bite, 0, 1) > 0) {
            ba.write(bite[0]);
            bite = new byte[1];
        }

        byte[] encryptedHex = ba.toByteArray();
        byte[] encrypted = SHA.getBytesFromHex(encryptedHex);
        data = AES.decrypt(encrypted, Startup.key, Startup.iv);
        bis = new ByteArrayInputStream(data);
        //TODO create object


        //TODO decode entry data; create object; decrypt fields; verify hash

    }

    @Override
    public int read() throws IOException {
        return this.bis.read();
    }

    @Override
    public int read(byte[] toStore) throws IOException {
        return this.bis.read(toStore);
    }

    @Override
    public int read(byte[] toStore, int off, int len) throws IOException {
        return this.bis.read(toStore, off, len);
    }

    public Can getObj() {
        return obj;
    }

}
