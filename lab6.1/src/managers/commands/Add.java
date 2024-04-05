package managers.commands;

import data.generators.IdGenerator;
import data.generators.PersonGenerator;
import managers.PersonManager;

public class Add implements Command{
    @Override
    public void execute(String[] args){
        PersonManager.add(PersonGenerator.createPerson(IdGenerator.generateId()));
    }
}
