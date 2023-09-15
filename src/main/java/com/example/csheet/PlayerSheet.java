package com.example.csheet;

import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.security.auth.callback.TextInputCallback;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static javafx.application.Platform.exit;

public class PlayerSheet implements java.io.Serializable {
    PlayerSheet(){

        _resources = new ArrayList<>();
        _maxHitPoints = 0;
        _abilityScores = new HashMap<>();
        _proficiencies = new ArrayList<>();

        _profBonus = 10;

        _features = new ArrayList<>();
    }

    public void loadFromJson(String jsonPath) {
        JSONParser parser = new JSONParser();

        try {
            Reader reader = new FileReader(jsonPath);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            _spellCastingAbility = (String) jsonObject.get("spellCastingAbility");
            _spellSaveDC = (int) (long) jsonObject.get("spellSaveDC");
            _numHitDice = (int) (long) jsonObject.get("numHitDice");
            _hitDice = (int) (long) jsonObject.get("hitDice");
            _name = (String) jsonObject.get("name");
            _spellAttackBonus = (int) (long) jsonObject.get("spellAttackBonus");
            _characterClass = (String) jsonObject.get("characterClass");
            _level = (int) (long) jsonObject.get("level");
            _profBonus = (int) (long) jsonObject.get("profBonus");
            _currentHitPoints = (int) (long) jsonObject.get("currHitPoints");
            _maxHitPoints = (int) (long) jsonObject.get("maxHitPoints");
            _tempHitPoints = (int) (long) jsonObject.get("tempHitPoints");
            _deathFailures = (int) (long) jsonObject.get("deathFailures");
            _deathSuccesses = (int) (long) jsonObject.get("deathSuccesses");
            _armourClass = (int) (long) jsonObject.get("ac");
            _speed = (int) (long) jsonObject.get("speed");
            _raceAge = (String) jsonObject.get("raceAge");
            _alignment = (String) jsonObject.get("alignment");
            _class = (String) jsonObject.get("class");
            _background = (String) jsonObject.get("background");

            JSONArray features = (JSONArray) jsonObject.get("features");
            for (Object obj: features) {
                JSONObject featureObject = (JSONObject) obj;
                Feature feature= new Feature((String) featureObject.get("name"),
                        (String) featureObject.get("description"));
                _features.add(feature);
            }

            JSONObject abilityScores = (JSONObject) jsonObject.get("abilityScores");
            for (Object obj: abilityScores.entrySet()) {
                Map.Entry<String, Long> scores = (Map.Entry<String, Long>) obj;
                _abilityScores.put(scores.getKey(),(int) (long) scores.getValue());
            }

            JSONObject proffs = (JSONObject) jsonObject.get("proffs");
            for (Object obj: proffs.entrySet()) {
                Map.Entry<String,JSONObject> prof = (Map.Entry<String, JSONObject>) obj;
                boolean hasProf = (boolean) prof.getValue().get("hasProff");
                String abilityScore = (String) prof.getValue().get("abilityScore");
                Proficiency proficiency = new Proficiency(abilityScore,prof.getKey(),hasProf);
                _proficiencies.add(proficiency);
            }

            JSONArray resources = (JSONArray) jsonObject.get("resources");

            for (Object obj: resources){
                JSONObject json = (JSONObject) obj;
                String name = (String) json.get("name");
                int current = (int) (long) json.get("current");
                int total = (int) (long) json.get("total");
                Resource resource = new Resource(total,current,name);

                _resources.add(resource);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            exit();
        }
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

        JSONArray features = new JSONArray();
        for (Feature entry: _features) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",entry._title);
            jsonObject.put("description",entry._desciption);
            features.add(jsonObject);
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

        JSONArray resources = new JSONArray();

        for (Resource resource: _resources){
            JSONObject json = new JSONObject();
            json.put("name",resource._name);
            json.put("current",resource._current);
            json.put("total",resource._total);

            resources.add(json);
        }
        jo.put("resources",resources);

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
    ArrayList<Feature> _features;
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
    ArrayList<Resource> _resources;
}
