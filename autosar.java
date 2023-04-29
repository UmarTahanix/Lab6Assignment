public class autosar implements Comparable<autosar>{
    private String UUID;
    private String short_name;
    private String long_name;

    public String getUUID(){ //UUID getter
        return UUID;
    }

    public void setUUID(String UUID){ //UUID setter
        this.UUID = UUID;
    }

    public String getShortName(){//SHORT-NAME getter
        return short_name;
    }

    public void setShortName(String short_name){//SHORT-NAME setter
        this.short_name = short_name;
    }

    public String getLongName(){//LONG-NAME getter
        return long_name;
    }

    public void setLongName(String long_name){//LONG-NAME setter
        this.long_name = long_name;
    }

    @Override
    public String toString(){ //returns the container tag string
        return 
       "    <CONTAINER " + this.getUUID() +">\n"
    +  "        <SHORT-NAME>" + this.getShortName() + "</SHORT-NAME>\n"
    +  "        <LONG-NAME>" + this.getLongName() + "</LONG-NAME>\n"
    +  "    </CONTAINER>\n" ;
    }

    @Override
    public int compareTo(autosar f) //overridden compareTo() nethod
    {
        if(this.getShortName().charAt(9) > f.getShortName().charAt(9)) //compare the letter after "Container" to sort the container tag
            return 1;
        else if (this.getShortName().charAt(9) < f.getShortName().charAt(9))
            return -1;
        else 
            return 0;
    }
}