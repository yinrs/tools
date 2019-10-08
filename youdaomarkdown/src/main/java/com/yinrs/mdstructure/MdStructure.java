package com.yinrs.mdstructure;

import java.util.List;

/**
 * Created by yinrs on 2019/9/24.
 */
public class MdStructure {
    private int floor;
    private String currentTitle;
    private List<MdStructure> subs;

    public MdStructure(int floor, String currentTitle) {
        this.floor = floor;
        this.currentTitle = currentTitle;
    }

    public String getCurrentTitle() {
        return currentTitle;
    }

    public void setCurrentTitle(String currentTitle) {
        this.currentTitle = currentTitle;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public List<MdStructure> getSubs() {
        return subs;
    }

    public void setSubs(List<MdStructure> subs) {
        this.subs = subs;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getCurrentTabs()).append(this.currentTitle);
        if (subs != null && subs.size() > 0) {
            sb.append("\n").append(getCurrentTabs()).append("\\begin{cases}").append("\n");
            for (int i = 0; i < subs.size(); i++) {
                sb.append(subs.get(i).toString()).append("\n");
                if (i != subs.size() - 1) {
                    sb.append(getSubsTabs()).append("\\\\\n");
                }
            }
            sb.append(getCurrentTabs()).append("\\end{cases}");
        }
        return sb.toString();
    }

    private String getCurrentTabs() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.floor; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    private String getSubsTabs() {
        return getCurrentTabs() + "\t";
    }
}
