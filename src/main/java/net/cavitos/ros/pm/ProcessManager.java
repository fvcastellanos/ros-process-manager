package net.cavitos.ros.pm;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.W32APIOptions;

public class ProcessManager {

    public static void main(String[] args) {
/*
        Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
        // Optional: wraps every call to the native library in a
        // synchronized block, limiting native calls to one at a time
        Kernel32 SYNC_INSTANCE = (Kernel32) Native.synchronizedLibrary(INSTANCE);

        System.out.println("Current thread: " + SYNC_INSTANCE.GetCurrentThread().toString());
        System.out.println("Current process: " + SYNC_INSTANCE.GetCurrentProcess().toString());

        Scanner scanner = new Scanner(System.in);

        scanner.nextInt();
*/

        Kernel32 kernel32 = (Kernel32) Native.loadLibrary(Kernel32.class, W32APIOptions.UNICODE_OPTIONS);
        Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();

        WinNT.HANDLE snapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
        try  {
            while (kernel32.Process32Next(snapshot, processEntry)) {
                System.out.printf("PID: %d, Name: %s, Size: %d\n", Integer.parseInt(processEntry.th32ProcessID.toString()),
                        Native.toString(processEntry.szExeFile), processEntry.size());
//                System.out.println(processEntry.th32ProcessID + "\t" + Native.toString(processEntry.szExeFile));
            }
        }
        finally {
            kernel32.CloseHandle(snapshot);
        }
    }


}

