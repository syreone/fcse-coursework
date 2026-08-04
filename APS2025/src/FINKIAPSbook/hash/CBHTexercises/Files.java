package FINKIAPSbook.hash.CBHTexercises;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

class File {
    String path;
    String name;
    String content;

    File (String path, String file, String content) {
        this.path = path;
        this.name = file;
        this.content = content;
    }

    @Override
    public String toString() {
        return path + name;
    }
}

public class Files {

    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, ArrayList<File>> fileSystem = new HashMap<>();

        int n = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");
            String path = tokens[0];
            String name = tokens[1];
            String content = tokens[2];
            File file = new File (path, name, content);
            if (!fileSystem.containsKey(content)) {
                fileSystem.put(content, new ArrayList<>());
                fileSystem.get(content).add(file);
            } else fileSystem.get(content).add(file);
        }

        int m = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");
            String command = tokens[0];
            String path = tokens[1];
            String name = tokens[2];
            String content = tokens[3];
            File file = new File (path, name, content);
            int idx;
            if (command.equals("add")) {
                if (!fileSystem.containsKey(content)) {
                    fileSystem.put(content, new ArrayList<>());
                    fileSystem.get(content).add(new File(path, name, content));
                } else fileSystem.get(content).add(new File(path, name, content));
            } else if (command.equals("delete")) {
                if (fileSystem.containsKey(content)) {
                    idx = fileSystem.get(content).indexOf(file);
                    if (idx > 0) fileSystem.get(content).remove(idx);
                }
            } else if (command.equals("find"))
                if (fileSystem.containsKey(content) && (fileSystem.get(content).indexOf(file) > 0)) System.out.println("true");
                else System.out.println("false");
        }

        String query = br.readLine();
        query = "(" + query + ")";
        if (fileSystem.containsKey(query)) for (File file : fileSystem.get(query)) System.out.print(file.toString() + " ");
    }
}