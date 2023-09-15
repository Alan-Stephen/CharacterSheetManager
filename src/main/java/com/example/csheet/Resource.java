package com.example.csheet;

public class Resource {
    Resource(int total, int current, String name){
        _total = total;
        _current = current;
        _name = name;
    }

    int _total;
    int _current;
    String _name;
}
