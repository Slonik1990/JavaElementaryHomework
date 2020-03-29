package Task22;

import Task20InOut.Student;
import Task20InOut.StudentGroup;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Practice {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();


        Student i = new Student("Ivan", "Ivanov");
        String ivan = objectMapper.writeValueAsString(i);
        StudentGroup x = objectMapper.readValue("{\"group\":[{\"lastName\":\"Petrov\",\"firstName\":\"Andrey\",\"marks\":[2,7,5,9,2,10,6,9,2,9]},{\"lastName\":\"Sidorov\",\"firstName\":\"Victor\",\"marks\":[3,6,7,2,4,9,5,10,4,9]},{\"lastName\":\"Zayceva\",\"firstName\":\"Irina\",\"marks\":[1,9,6,2,8,2,7,1,3,2]},{\"lastName\":\"Berkut\",\"firstName\":\"Zahar\",\"marks\":[6,10,6,2,4,4,3,6,4,8]},{\"lastName\":\"Boyko\",\"firstName\":\"Nykolay\",\"marks\":[1,5,10,4,10,3,1,6,8,8]}]}", StudentGroup.class );
        System.out.println(x);
    }
}
