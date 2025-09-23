package yl.greta.file;

import yl.greta.model.Livre;

import java.io.IOException;

public interface Fichier {

    public void readFile() throws IOException;
    public void writeFile() throws IOException, ClassNotFoundException;


}
