package com.plus.thread.example;


import com.plus.thread.ThreadScopeData;

class A {
    public String name;
}

public class MainTest {

    public static void main(String args[]) {
        ThreadScopeData<A> t = ThreadScopeData.getThreadInstance();
        t.setData(new A());
        A a = t.getDate();
        System.err.println(a);
        A a1 = (A) ThreadScopeData.getThreadInstance().getDate();
        System.err.println(a);
    }
}
