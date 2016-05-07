package net.cavitos.ros.pm;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.W32APIOptions;
import net.cavitos.ros.pm.dto.ProcessInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class ProcessService {

    private ProcessInfo buildProcessInfo(Tlhelp32.PROCESSENTRY32.ByReference processEntry) {
        ProcessInfo pi = ProcessInfo.newBuilder()
            .withPId(Integer.parseInt(processEntry.th32ProcessID.toString()))
            .withName(Native.toString(processEntry.szExeFile))
            .withMemoryUssage(new Long(processEntry.size()))
            .build();

        return pi;
    }

    public List<ProcessInfo> getProcessList() {

        List<ProcessInfo> processList = new ArrayList<ProcessInfo>();

        Kernel32 kernel32 = (Kernel32) Native.loadLibrary(Kernel32.class, W32APIOptions.UNICODE_OPTIONS);
        Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();

        WinNT.HANDLE snapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
        try  {
            while (kernel32.Process32Next(snapshot, processEntry)) {
                processList.add(buildProcessInfo(processEntry));
            }
        }
        finally {
            kernel32.CloseHandle(snapshot);
        }

        return processList;

    }

    public void killProcess(Long id) {
        throw new NotImplementedException();
    }
}
