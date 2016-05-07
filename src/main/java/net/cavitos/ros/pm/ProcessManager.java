package net.cavitos.ros.pm;

import net.cavitos.ros.pm.dto.ProcessInfo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.util.List;

public class ProcessManager {

    private ObjectMapper objectMapper;
    private ProcessService processService;

    public ProcessManager() {
        processService = new ProcessService();
        objectMapper = new ObjectMapper();
    }

    public void writeProcessListToFile() {
        try {
            List<ProcessInfo> processInfoList = processService.getProcessList();
            objectMapper.writeValue(new File("process-list.json"), processInfoList);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProcessManager pm = new ProcessManager();
        if(args.length > 0) {
            String op = args[0].toLowerCase();
            if(op.equals("list")) {
                pm.writeProcessListToFile();
            }
        } else {
            System.out.println("Parameters: list, kill <pid>");
        }
    }


}

