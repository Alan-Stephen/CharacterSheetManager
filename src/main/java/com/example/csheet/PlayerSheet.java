package com.example.csheet;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerSheet {
    PlayerSheet(){

        _maxHitPoints = 0;
        _abilityScores = new HashMap<>();
        _abilityScores.put("Strength",20);
        _abilityScores.put("Consitution",19);
        _abilityScores.put("Dexterity",18);
        _abilityScores.put("Charisma",8);
        _abilityScores.put("Intelligence",7);
        _abilityScores.put("Wisdom",10);

        _proficiencies = new ArrayList<>();
        _proficiencies.add(new Proficiency("Dexterity","Stealth",true));
        _proficiencies.add(new Proficiency("Charisma","Healf",true));
        _proficiencies.add(new Proficiency("Strength","heai",true));
        _proficiencies.add(new Proficiency("Intelligence","heai",true));
        _proficiencies.add(new Proficiency("Wisdom","heai",true));
        _proficiencies.add(new Proficiency("Dexterity","Stealth",true));
        _proficiencies.add(new Proficiency("Charisma","Healf",true));
        _proficiencies.add(new Proficiency("Strength","heai",true));
        _proficiencies.add(new Proficiency("Intelligence","heai",true));
        _proficiencies.add(new Proficiency("Wisdom","heai",true));
        _proficiencies.add(new Proficiency("Dexterity","Stealth",true));
        _proficiencies.add(new Proficiency("Charisma","Healf",true));
        _proficiencies.add(new Proficiency("Strength","heai",true));
        _proficiencies.add(new Proficiency("Intelligence","heai",true));
        _proficiencies.add(new Proficiency("Wisdom","heai",true));
        _proficiencies.add(new Proficiency("Wisdom","heai",true));
        _proficiencies.add(new Proficiency("Wisdom","heai",true));
        _proficiencies.add(new Proficiency("Wisdom","heai",true));

        _profBonus = 10;

        _features = new ArrayList<>();
        _features.add(new Pair<>("1","Does 13d10 to everyone and kills dm"));
        _features.add(new Pair<>("2","Does 13d10 to everyone and kills dDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmm"));
        _features.add(new Pair<>("3","Does 13d10 to everyone and kills dDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmm"));
        _features.add(new Pair<>("4","Does 13d10 to everyone and kills dDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmm"));
        _features.add(new Pair<>("5","Does 13d10 to everyone and kills dDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmm"));
        _features.add(new Pair<>("6","Does 13d10 to everyone and kills dDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmm"));
    }

    ArrayList<Pair<String, String>> _features;
    String _spellCastingAbility;
    int _spellSaveDC;
    int _spellAttackBonus;
    Spellbook _spellbook;
    int _numHitDic;
    int _hitDice;
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
