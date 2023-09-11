package com.example.csheet;

import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerSheet implements java.io.Serializable {
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
        _proficiencies.add(new Proficiency("Dexterity","Saving Throw",false));
        _proficiencies.add(new Proficiency("Dexterity","Stealth",false));
        _proficiencies.add(new Proficiency("Dexterity","Acrobatics",false));
        _proficiencies.add(new Proficiency("Dexterity","Slight of Hand",false));
        _proficiencies.add(new Proficiency("Strength","Saving Throw",false));
        _proficiencies.add(new Proficiency("Strength","Athletics",false));
        _proficiencies.add(new Proficiency("Consitution","Saving Throw",false));
        _proficiencies.add(new Proficiency("Intelligence","Saving Throw",false));
        _proficiencies.add(new Proficiency("Intelligence","Arcana",false));
        _proficiencies.add(new Proficiency("Intelligence","History",false));
        _proficiencies.add(new Proficiency("Intelligence","Investigation",false));
        _proficiencies.add(new Proficiency("Intelligence","Nature",false));
        _proficiencies.add(new Proficiency("Intelligence","Religion",false));
        _proficiencies.add(new Proficiency("Wisdom","Saving Throw",false));
        _proficiencies.add(new Proficiency("Wisdom","Insight",false));
        _proficiencies.add(new Proficiency("Wisdom","Animal Handling",false));
        _proficiencies.add(new Proficiency("Wisdom","Medicine",false));
        _proficiencies.add(new Proficiency("Wisdom","Perception",false));
        _proficiencies.add(new Proficiency("Wisdom","Survival",false));
        _proficiencies.add(new Proficiency("Charisma","Saving Throw",false));
        _proficiencies.add(new Proficiency("Charisma","Deception",false));
        _proficiencies.add(new Proficiency("Charisma","Intimidation",false));
        _proficiencies.add(new Proficiency("Charisma","Performance",false));
        _proficiencies.add(new Proficiency("Charisma","Persuasion",false));

        _profBonus = 10;

        _features = new ArrayList<>();
        _features.add(new Pair<>("1","Does 13d10 to everyone and kills dm"));
        _features.add(new Pair<>("2","Does 13d10 to everyone and kills dDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmm"));
        _features.add(new Pair<>("3","Does 13d10 to everyone and kills dDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmm"));
        _features.add(new Pair<>("4","Does 13d10 to everyone and kills dDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmm"));
        _features.add(new Pair<>("5","Does 13d10 to everyone and kills dDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmm"));
        _features.add(new Pair<>("6","Does 13d10 to everyone and kills dDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmDoes 13d10 to everyone and kills dmm"));
    }

    public void toJson(String jsonPath){
        JSONObject jo = new JSONObject();
        jo.put("spellCastingAbility",_spellCastingAbility);
        jo.put("spellSaveDC",_spellSaveDC);
        jo.put("numHitDice",_numHitDice);
        jo.put("hitDice",_hitDice);
        jo.put("name",_name);
        jo.put("spellAttackBonus",_spellAttackBonus);
        jo.put("characterClass",_characterClass);
        jo.put("level",_level);
        jo.put("profBonus",_profBonus);
        jo.put("currHitPoints",_currentHitPoints);
        jo.put("maxHitPoints",_maxHitPoints);
        jo.put("tempHitPoints",_tempHitPoints);
        jo.put("deathFailures",_deathFailures);
        jo.put("deathSuccesses",_deathSuccesses);
        jo.put("ac",_armourClass);
        jo.put("speed",_speed);
        jo.put("raceAge",_raceAge);
        jo.put("alignment",_alignment);
        jo.put("class",_class);
        jo.put("background",_background);

        JSONObject features = new JSONObject();
        for (Pair<String, String> entry: _features) {
            features.put(entry.getKey(),entry.getValue());
        }

        jo.put("features",features);

        JSONObject abilityScores = new JSONObject();
        for (Map.Entry<String, Integer> entry: _abilityScores.entrySet()) {
            abilityScores.put(entry.getKey(),entry.getValue());
        }

        jo.put("abilityScores",abilityScores);

        JSONObject proffs = new JSONObject();
        for (Proficiency proficiency: _proficiencies) {
            JSONObject prof = new JSONObject();
            prof.put("abilityScore", proficiency._abilityScore);
            prof.put("hasProff", proficiency._hasProficiency);
            proffs.put(proficiency._name,prof);
        }

        jo.put("proffs",proffs);

        try {
            FileWriter writer = new FileWriter(jsonPath);
            System.out.println(jo.toJSONString());
            String json = jo.toJSONString();
            writer.write(json);
            writer.flush();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Pair<String, String>> _features;
    String _spellCastingAbility;
    int _spellSaveDC;
    int _spellAttackBonus;
    Spellbook _spellbook;
    int _numHitDice;
    int _hitDice;
    String _name;
    String _raceAge;
    String _characterClass;
    int _level;
    HashMap<String,Integer> _abilityScores;
    ArrayList<Proficiency> _proficiencies;
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

    String _alignment;
    String _class;
    String _background;
}
