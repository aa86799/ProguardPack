package com.stone.myapplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * author : stone
 * time   : 15/4/18 20 01
 * email  : aa86799@163.com
 */
public class BeanSort {

    ArrayList<User> list = new ArrayList();
    public void compare() {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User lhs, User rhs) {
                if (lhs.getAge() < rhs.getAge())
                    return -1;
                else if (lhs.getAge() == rhs.getAge())
                    return 0;
                else
                    return 1;
            }
        });
    }
}
