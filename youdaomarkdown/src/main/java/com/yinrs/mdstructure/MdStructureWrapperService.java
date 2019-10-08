package  com.yinrs.mdstructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by yinrs on 2019/9/24.
 */
public class MdStructureWrapperService {
    public MdStructure wrapMdStructure(String mdTitle, String mdContent) {
        //init
        MdStructure root = new MdStructure(0, mdTitle);
        try {
            Integer oldFloor = 0;
            Stack<Integer> stack = new Stack<>();
            Map<Integer, MdStructure> floorMdMap = new HashMap<>();
            stack.push(oldFloor);
            floorMdMap.put(oldFloor, root);

            String[] strArr = mdContent.split("\n");
            for (String line : strArr) {
                if (line.contains("# ")) {
                    Integer newFloor = countFloor(line);
                    do {
                        oldFloor = stack.pop();
                    } while (newFloor < oldFloor);

                    if (newFloor > oldFloor) {//sub
                        MdStructure newMd = new MdStructure(newFloor, getCurrentTitle(line));
                        MdStructure parent = floorMdMap.get(oldFloor);
                        parent.setSubs(new ArrayList<MdStructure>());
                        parent.getSubs().add(newMd);

                        stack.push(oldFloor);
                        stack.push(newFloor);
                        floorMdMap.put(newFloor, newMd);
                    } else {//sib
                        MdStructure newMd = new MdStructure(newFloor, getCurrentTitle(line));
                        MdStructure parent = floorMdMap.get(oldFloor - 1);
                        parent.getSubs().add(newMd);
                        stack.push(newFloor);
                        floorMdMap.put(newFloor, newMd);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(root);
        }
        return root;
    }

    private int countFloor(String line) {
        return line.lastIndexOf("#") + 1;
    }

    private String getCurrentTitle(String line) {
        return line.split("# ")[1].trim();
    }
}
