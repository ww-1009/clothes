package cn.itcast.handheldclass.spinners;
import java.util.ArrayList;
import java.util.List;

public class InformationFilling_spinner_sex {
    public static List<String> GetSexList() {
// Create a List<String> for sex
        List<String> sex = new ArrayList<String>();

//        sex.add("");
        sex.add("男");
        sex.add("女");
        sex.add("未知");
        return sex;
    }

}
