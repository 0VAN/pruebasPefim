package com.pefim;

import java.io.Serializable;

/**
 * Created by juanfranfv on 7/18/17.
 */
public class Item implements Serializable {
    public int item;
    public long utility = 0;

    public Item(int i, long u){
        this.item = i;
        this.utility = u;
    }

    public int getItem(){ return this.item; }
    public long getUtility(){ return this.utility; }

    public void setItem(int item){ this.item = item; }
    public void setUtility(int utility){ this.utility = utility; }

    public String toString(){
        // use a string buffer for more efficiency
        StringBuffer r = new StringBuffer();
        r.append("item: " + this.item);
        r.append(" utility: " + this.utility);
        return r.toString(); // return the tring
    }
}
