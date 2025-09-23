package yl.greta.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Emprunts<ID, K, V> implements Serializable {
    private ID clientId;
    private Map<K, V> map = new HashMap<>();

    public Emprunts(ID clientId) { this.clientId = clientId; }

    public void ajouter(K key, V value) { map.put(key, value); }

    public Map<K,V> getAll() { return map; }
}
