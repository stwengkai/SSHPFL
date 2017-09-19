package Test.SSH.SSHPFL;

import com.jcraft.jsch.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SSHClient
{

    public SSHClient()
    {
    }

    public void redirect(String privateSSHKeyPath, String sshHost, int sshPort, String sshUser, String path)
    {
        JSch jsch = new JSch();
        try
        {
            jsch.addIdentity(privateSSHKeyPath);
            Session session = jsch.getSession(sshUser, sshHost, sshPort);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            List portForwardingList = getPortForwardingList(path);
            for(int i = 0; i < portForwardingList.size(); i++)
            {
                String mapString[] = ((String)portForwardingList.get(i)).split(",");
                if(mapString.length == 3)
                {
                    int localPort = Integer.parseInt(mapString[0].trim());
                    String remoteHost = mapString[1].trim();
                    int remotePort = Integer.parseInt(mapString[2].trim());
                    session.setPortForwardingL(localPort, remoteHost, remotePort);
                }
            }

        }
        catch(JSchException e)
        {
            e.printStackTrace();
        }
        catch(IOException ioe)
        {
            System.out.println("load file error!");
            ioe.printStackTrace();
        }
    }

    private List getPortForwardingList(String path)
        throws IOException
    {
        List list = new ArrayList();
        File filename = new File(path);
        FileInputStream fin = new FileInputStream(filename);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader br = new BufferedReader(reader);
        for(String line = br.readLine(); line != null; line = br.readLine())
            list.add(line);

        br.close();
        return list;
    }
}