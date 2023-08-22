import java.util.*;
import java.io.*;

class Packer {
    public static void main(String Arg[]) {
        byte Buffer[] = new byte[1024];
        int iRet = 0;
        int PackCount = 0;

        Scanner sobj = new Scanner(System.in);

        System.out.println("------------------Marvellous Packer Unpacker-------------------- ");
        System.out.println("Packing Activity of the application is started");
        System.out.println("Enter the name of folder which contains the files that you want to pack");
        String FolderName = sobj.nextLine();

        File fobj = new File(FolderName);
        String Header = null;

        System.out.println("Enter the name of packed file that you want to create: ");
        String PackFile = sobj.nextLine();

        try {

            File Packobj = new File(PackFile);
            boolean bRet = Packobj.createNewFile();
            if (bRet == false) {
                System.out.println("Unable to create packed file");
                return;
            }

            System.out.println("Packed files gets successfully creates in your current directory: ");
            FileOutputStream outobj = new FileOutputStream(Packobj);

            bRet = fobj.isDirectory();
            if (bRet == true) {
                File List[] = fobj.listFiles();

                System.out.println("Total number of files in the directory are : " + List.length);
                for (int i = 0; i < List.length; i++) {
                    if ((List[i].getName()).endsWith(".txt")) {
                        Header = List[i].getName() + " " + List[i].length();
                        for (int j = Header.length(); j < 100; j++) {
                            Header = Header + " ";
                        }
                        byte bHeader[] = Header.getBytes();
                        outobj.write(bHeader, 0, bHeader.length);

                        FileInputStream inobj = new FileInputStream(List[i]);
                        while ((iRet = inobj.read(Buffer)) != -1) {
                            outobj.write(Buffer, 0, iRet);
                        }
                        System.out.println("File successfully packed with name: " + List[i].getName());
                        inobj.close();
                        PackCount++;
                    }
                }
                System.out.println("--------------------------Packing Summary---------------------");
                System.out.println("Total number of files scanned" + List.length);
                System.out.println("Total number of files packed" + PackCount);

                System.out.println("Thank you for using Marvellous Packer Unpacker...");
            }
        } catch (Exception iobj) {
            System.out.println("Exception occured : " + iobj);
        }
    }
}