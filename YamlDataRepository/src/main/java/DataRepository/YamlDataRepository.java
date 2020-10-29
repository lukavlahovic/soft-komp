package DataRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.Entity;
import repository.DataRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YamlDataRepository implements DataRepository {

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    @Override
    public void save(String s, Object o) {

        try {
            List<Entity> objects = mapper.readValue(new File(s), new TypeReference<List<Entity>>() {
            });
            objects.add((Entity)o);
            mapper.writeValue(new File(s),objects);

        } catch (IOException e) {
            List<Entity> objects1 = new ArrayList<>();
            objects1.add((Entity)o);
            try {
                mapper.writeValue(new File(s),objects1);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
           // e.printStackTrace();
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
    public void delete(String s, int id) {

        try {
            List<Entity> objects = mapper.readValue(new File(s), new TypeReference<List<Entity>>() {
            });
            for(Entity entity : objects){
                if(entity.getId()==id) {
                    objects.remove(entity);
                    break;
                }
            }
            mapper.writeValue(new File(s), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(String s, int id, HashMap<Object, Object> updatedMap) {

        try {
            List<Entity> objects = mapper.readValue(new File(s), new TypeReference<List<Entity>>() {
            });
            for(Entity entity : objects){
                if(entity.getId()==id) {
                    updatedMap.forEach((key,value)-> entity.getAttributes().put(key,value));
                    break;
                }
            }
            mapper.writeValue(new File(s), objects);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}