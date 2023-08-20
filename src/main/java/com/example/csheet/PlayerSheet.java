package com.example.csheet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerSheet {
    PlayerSheet(){
        _abilityScores = new HashMap<>();
        _abilityScores.put("Strength",20);
        _abilityScores.put("Consitution",19);
        _abilityScores.put("Dexterity",18);
        _abilityScores.put("Charisma",8);
        _abilityScores.put("Intelligence",7);
        _abilityScores.put("Wisdom",10);
    }
    String spellCastingAbility;
    int spellSaveDC;
    int spellAttackBonus;
    Spellbook spellbook;
    int numHitDic;
    int hitDice;
    String _name;
    String _race;
    String characterClass;
    int _level;
    HashMap<String,Integer> _abilityScores;
    ArrayList<Proficiency> _proficiencies;
    HashMap<String, Boolean> _savingThrows;
    int _profBonus;
    int _currentHitPoints;
    int _maxHitPoints;
    int _tempHitPoints;
    int _deathFailures;
    int _deathSuccesses;
    int _armourClass;
    int _speed;
    Wallet _wallet;
    ArrayList<Item> items;
}
