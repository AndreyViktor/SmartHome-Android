package br.com.andrey.projetointegradoapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by andrey on 01/11/2016.
 */
public class UDP implements Runnable {
    private final String message;
    private final String IPadress;

    public UDP(String message, String moduleIpAdress) {
        this.message = (message);
        this.IPadress = moduleIpAdress;
    }

    @Override
    public void run() {
            try {
                String messageStr = message;
                int server_port = 5000;
                DatagramSocket s = new DatagramSocket();
                InetAddress local = InetAddress.getByName(this.IPadress);
                int msg_length = messageStr.length();
                byte[] message = messageStr.getBytes();
                DatagramPacket p = new DatagramPacket(message, msg_length, local,
                        server_port);
                s.send(p);
            }
            catch (Exception e)
            {

            }
    }

}
