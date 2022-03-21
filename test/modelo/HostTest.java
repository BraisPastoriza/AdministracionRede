
package modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bpastgarr
 */
public class HostTest {
    
    @Test
    public void validarIP() {
        assertTrue(Host.validarIP("192.168.0.9"));
        assertTrue(Host.validarIP("192.168.100.0"));
        assertTrue(Host.validarIP("255.255.255.255"));
        assertTrue(Host.validarIP("0.0.0.0"));

        assertFalse(Host.validarIP(""));
        assertFalse(Host.validarIP("..."));
        assertFalse(Host.validarIP("192.168..9"));
        assertFalse(Host.validarIP("192.168:0.9"));
        assertFalse(Host.validarIP("192.16x.0.9"));
        assertFalse(Host.validarIP("192.168.0.009"));
        assertFalse(Host.validarIP("192.168.0.9."));
        assertFalse(Host.validarIP(".192.168.0.9"));
        assertFalse(Host.validarIP("256.0.0.0"));
        assertFalse(Host.validarIP("255.256.0.0"));
    }

    @Test
    public void buscarIP() {
        String[] ips = {"192.168.0.9", "192.168.100.0", "255.255.255.255", "0.0.0.0"};
        String[] vacio = new String[0];

        assertEquals(Host.buscarIP("192.168.0.9", ips), 0);
        assertEquals(Host.buscarIP("192.168.100.0", ips), 1);
        assertEquals(Host.buscarIP("255.255.255.255", ips), 2);
        assertEquals(Host.buscarIP("0.0.0.0", ips), 3);

        assertEquals(Host.buscarIP("192.168.100.9", ips), -1);
        assertEquals(Host.buscarIP("192.168.0.009", ips), -1);
        assertEquals(Host.buscarIP("", ips), -1);
        assertEquals(Host.buscarIP("0.0.0.0", vacio), -1);
    }

    @Test
    public void validarMac() {
        assertTrue(Host.validarMac("5C260A242A60"));
        assertTrue(Host.validarMac("5C:26:0A:24:2A:60"));
        assertTrue(Host.validarMac("5C-26-0A-24-2A-60"));
        assertTrue(Host.validarMac("5C26.0A24.2A60"));
    }
    
}
