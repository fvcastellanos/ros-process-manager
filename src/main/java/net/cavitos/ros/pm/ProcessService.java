package net.cavitos.ros.pm;

import net.cavitos.ros.pm.dto.ProcessInfo;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarPermissionDeniedException;
import org.hyperic.sigar.SigarProxy;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import org.hyperic.sigar.cmd.Kill;

public class ProcessService {
    
    private SigarProxy proxy;
    
    public ProcessService() {
        proxy = new Sigar();
    }
    
    private String getProcessName(long id) {
        String name = "???";
        try {
            name = proxy.getProcExe(id).getName();
        } catch(SigarPermissionDeniedException ex) {
            name = "Denied";
        } catch (Exception ex) {

        }

        return name;
    }

    private Long getProcessMemory(long id) {
        Long size = 0L;
        try {
            ProcMem mem = proxy.getProcMem(id);
            size = (mem.getSize() /1024) / 1024;
        } catch(Exception ex) {

        }

        return size;
    }

    public List<ProcessInfo> getProcessList() {
        List<ProcessInfo> processList = new ArrayList<ProcessInfo>();
//        SigarProxy proxy = new Sigar();

        try {
            ProcessInfo info;
            long [] idList  = proxy.getProcList();

            for(long id : idList) {
                String name = getProcessName(id);
                if(!name.equals("Denied")) {
                    info = ProcessInfo.newBuilder()
                        .withPId(id)
                        .withName(name)
                        .withMemoryUssage(getProcessMemory(id))
                        .build();
                    processList.add(info);
                }
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return processList;
    }

    public boolean killProcess(Long id) {
        boolean killed = true;
        try {
            String signal = "SIGTERM";
            Sigar sigarKill = new Sigar();
            sigarKill.kill(id, signal);
        } catch(Exception ex) {
            killed = false;
        }
        
        return killed;
    }
}
