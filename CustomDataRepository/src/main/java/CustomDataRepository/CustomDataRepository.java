package CustomDataRepository;

import CusstomDataRepositoryParser.CustomParser;
import model.Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomDataRepository implements repository.DataRepository {

    CustomParser parser = new CustomParser();
    @Override
    public void save(String s, Object o) {

            List<Entity> objects = parser.reader(new File(s));
            if(!objects.isEmpty()) {
                objects.add((Entity) o);
                parser.writer(new File(s), objects);
            }
            else {
                List<Entity> objects1 = new ArrayList<>();
                objects1.add((Entity) o);
                parser.writer(new File(s), objects1);
            }
    }

    @Override
    public <T> T findById(String s, String s1, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> List<T> findAll(String s, Class<T> aClass) {
        return null;
    }

    @Override
    public void delete(String s, int i) {

    }

    @Override
    public void update(String s, int i, HashMap<Object, Object> hashMap) {

    }
}