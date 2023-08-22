package com.example.csheet;

public class Proficiency {
    Proficiency(String abilityName, String name, Boolean hasProficiency){
        _abilityScore = abilityName;
        _name = name;
        _hasProficiency = hasProficiency;
    }
    String _abilityScore;
    String _name;
    Boolean _hasProficiency;
}
