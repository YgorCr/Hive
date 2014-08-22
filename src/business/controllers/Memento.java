package business.controllers;

import java.util.HashMap;

public class Memento {
	 private final HashMap<String, Object> state;
	 
     public Memento(HashMap<String, Object> stateToSave) {
         state = stateToSave;
     }

     public HashMap<String, Object> getSavedState() {
         return state;
     }
}
