package com.example.herve.recycleviewcount.bean;

/**
 * Created           :Herve on 2016/9/13.
 *
 * @ Author          :Herve
 * @ e-mail          :lijianyou.herve@gmail.com
 * @ LastEdit        :2016/9/13
 * @ projectName     :RecycleViewCount
 * @ version
 */
public class ScreenBean {

    public int dmW;
    public int dmH;

    public int getDmW() {
        return dmW;
    }

    public void setDmW(int dmW) {
        this.dmW = dmW;
    }

    public int getDmH() {
        return dmH;
    }

    public void setDmH(int dmH) {
        this.dmH = dmH;
    }

    @Override
    public String toString() {
        return "ScreenBean{" +
                "dmW=" + dmW +
                ", dmH=" + dmH +
                '}';
    }
}
