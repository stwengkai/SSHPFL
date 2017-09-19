package Test.SSH.SSHPFL;

//Referenced classes of package main.im.fenqi.ssh:
//         SSHClient

public class SSHMain
{

 public SSHMain()
 {
 }

 public static void main(String args[])
 {
     SSHClient client = new SSHClient();
     String privateKeyPath = args[0];
     String jumpHost = args[1];
     int sshPort = 22;
     String jumpUser = args[2];
     String file = args[3];
     client.redirect(privateKeyPath, jumpHost, sshPort, jumpUser, file);
 }
}
