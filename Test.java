import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){//Omar Al-Tahan 2001277
    try{
        String fileName = args[0]; //type the file name in cmd = "sample.arxml"
        if (!(fileName.endsWith(".arxml"))) //if the file name doesn't end with extension".arxml"
            throw new NotVaildAutosarFileException();
        File file = new File(fileName);//make file its name is fileName "sample.arxml"
        FileInputStream inputStream = new FileInputStream(file);// like a channel to transfer the file required to be read
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        if ((i=inputStream.read()) == -1) //if the file is empty "read() method returns integer , so i is required"
            throw new EmptyAutosarFileException();
        while((i=inputStream.read()) != -1){ //as long as the file is not empty
            stringBuilder.append((char) i); //store what is in the file char-by-char in the stringbuilder
        }
        String data = stringBuilder.toString(); 
        Scanner scanner = new Scanner (data); //read the data of the file char-by-char
        ArrayList<autosar> containers = new ArrayList<>(); //create ArrayList of type autosar
        while(scanner.hasNextLine())//as long as the file ins't empty
        {
            String line = scanner.nextLine();// to store UUID
            if (line.contains("<CONTAINER")) //when the line contains <CONTAINER , start storing the following
            {
                String ContainerID = line.substring(line.indexOf("UUID="),line.indexOf(">")); //from "UUID=" to ">"
                String SHORTNAME = scanner.nextLine(); // to store short name
                String short_name = SHORTNAME.substring(SHORTNAME.indexOf(">")+1,SHORTNAME.indexOf("</")); //from after ">" until before "</"
                String LONGNAME = scanner.nextLine(); // to store long name
                String long_name = LONGNAME.substring(LONGNAME.indexOf(">")+1,LONGNAME.indexOf("</")); //from after ">" until before "</"
                autosar container = new autosar(); //object for CONTAINER tag
                container.setUUID(ContainerID); //set  UUID
                container.setShortName(short_name); //set SHORT-NAME
                container.setLongName(long_name); // set LONG-NAME
                containers.add(container);//add CONTAINER tag to the ArrayList
            }
        }

        Collections.sort(containers); //sort the CONTAINER tag based on the implementation of the overridden compareTo method in "autosar" class 

        String outName = fileName.substring(0,fileName.indexOf(".")) + "_mod.arxml"; //file name of the output "the same name until before .arxml => add "_mod.arxml"
        FileOutputStream outputStream = new FileOutputStream(outName);//to write the output file
        outputStream.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes()); //write this sentence
        outputStream.write("<AUTOSAR>\n".getBytes());//write this sentence
        for (int j = 0; j < containers.size(); j++){// write the sorted containers
            outputStream.write(containers.get(j).toString().getBytes()); //outputStream writes bytes , so getBytes() method is used to transform bytes into text
            }
        outputStream.write("</AUTOSAR>\n".getBytes());//write the last sentence
        }
    
        catch (FileNotFoundException e) { //handle the exception of file not existing 
            e = new FileNotFoundException("File not found!");
        }
    
        catch (IOException e) { //handle the exception in stream either i/o
            e = new IOException("IO Exception!");
        }

        catch (NotVaildAutosarFileException e){//handle the exception of invalid file extension by printing the warning
            System.out.println("Invalid File Extension!");
        }

        catch (EmptyAutosarFileException e){//handle the exception of empty file by printing the warning
            System.out.println("Empty File!");
        }
    }
}