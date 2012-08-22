package org.xmlrpc;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlrpc.android.MethodCall;
import org.xmlrpc.android.XMLRPCServer;

import android.app.IntentService;
import android.content.Intent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class XMLRPCServerTest extends IntentService {
    public XMLRPCServerTest() {
        super("XMLRPCServerTest");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            ServerSocket socket = new ServerSocket(8888);
            XMLRPCServer server = new XMLRPCServer();
            while (true) {
                Socket client = socket.accept();
                MethodCall call = server.readMethodCall(client);
                String name = call.getMethodName();
                if (name.equals("add")) {
                    ArrayList<Object> params = call.getParams();
                    // assume "add" method has two Integer params, so no checks done
                    int i0 = (Integer) params.get(0);
                    int i1 = (Integer) params.get(1);
                    server.respond(client, new Object[] {i0 + i1});
                } else
                if (name.equals("add1")) {
                    ArrayList<Object> params = call.getParams();
                    // assume "add" method has two Integer params, so no checks done
                    int i0 = (Integer) params.get(0);
                    int i1 = (Integer) params.get(1);
                    server.respond(client, i0 + i1);
                } else
                if (name.equals("test0")) {
                    // returns 3 values: String, int and boolean
                    server.respond(client, "returns 3 values: String, int and boolean", 0, false);
                } else
                if (name.equals("test1")) {
                    // returns 3 values: String, int and boolean
                    Object[] arr = new Object[] {
                            "returns 3 values: String, int and boolean", 1, false
                    };
                    server.respond(client, arr);
                } else
                if (name.equals("test2")) {
                    // returns 1 value: an array of String, int and boolean
                    Object[] arr = new Object[] {
                            "returns 1 value: an array of String, int and boolean", 2, false
                    };
                    server.respond(client, (Object) arr);
                } else {
                    server.respond(client, null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
