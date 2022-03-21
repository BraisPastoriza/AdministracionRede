package modelo;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author bpastgarr
 */
public class Host {

   static String nombre;
   static String ip;
   static String mascara;
   static String mac;
   static String gateway;
   static String[] dns;

    public Host() {
    }

    public Host(String nombre) {
        this.nombre = nombre;
    }

    public Host(String ip, String mascara) {
        this.ip = ip;
        this.mascara = mascara;
    }

    public Host(String nombre, String ip, String mascara) {
        this.nombre = nombre;
        this.ip = ip;
        this.mascara = mascara;
    }

    public Host(String nombre, String ip, String mascara, String gateway, String[] dns) {
        this.nombre = nombre;
        this.ip = ip;
        this.mascara = mascara;
        this.gateway = gateway;
        this.dns = dns;
    }

    public static boolean validarIP(String ip) {

        String cerosIzq = "00";
        boolean esValida = true;
        int i = 0;
        final int NUM_PUNTOS = 3;
        int cuentaPuntos = 0;

        if (ip.length() == 0) {
            esValida = false;
        } else {

            do {
                if (ip.charAt(i) == '.') {
                    cuentaPuntos++;
                }
                if (Character.isLetter(ip.charAt(i))) {
                    esValida = false;
                }
                i++;
            } while (i < ip.length() && esValida);

            if (cuentaPuntos != NUM_PUNTOS) {
                esValida = false;
            }
            if (esValida) {
                ip = ip.replace(".", " ");
                String octetos[];
                octetos = ip.split(" ");

                if (octetos.length != 4) {
                    esValida = false;
                } else {
                    int octeto;
                    for (int j = 0; j < octetos.length; j++) {
                        if (octetos[j].equals("")) {
                            esValida = false;
                            break;

                        } else {
                            if (octetos[j].length() > 1 && octetos[j].charAt(0) == '0') {
                                esValida = false;
                            } else {
                                octeto = Integer.parseInt(octetos[j]);
                                if (octeto < 0 || octeto > 255) {
                                    esValida = false;
                                    break;
                                }
                            }

                        }

                    }
                }
            }
        }

        return esValida;
    }

    @Override
    public String toString() {
        String cad = "";
        cad += "Host\n";
        cad += "====\n";
        cad += "Nombre: " + nombre + "\n";
        cad += "Mac: " + mac + "\n";
        cad += "IP: " + ip + "\n";
        cad += "Máscara: " + mascara + "\n";
        cad += "Puerta de enlace: " + gateway + "\n";
        cad += "Servidor/es DNS: " + Arrays.toString(dns) + "\n";
        return cad;
    }

    public static boolean validarMascara(String mascara) {
        boolean ipOk = validarIP(mascara);
        boolean mascaraOk = false;
        String[] aux = mascara.split("\\.");
        String[] valores = {"128", "192", "224", "240", "248", "252", "254", "255"};

        if (ipOk) {
            mascaraOk = octeto1Ok(mascara);
            if (aux[0].equals("255") && !Objects.equals(aux[1], "0")) {
                mascaraOk = octeto2Ok(mascara);

            }

            if (aux[0].equals("255") && aux[1].equals("255") && !Objects.equals(aux[2], "0")) {
                mascaraOk = octeto3Ok(mascara);

            }

            if (aux[0].equals("255") && aux[1].equals("255") && aux[2].equals("255") && !Objects.equals(aux[3], "0")) {
                mascaraOk = octeto4Ok(mascara);
            }
        }

        return mascaraOk;

    }

    public static int buscarIP(String ip, String[] ips) {

        int pos;
        try {
            for (int i = 0; i < ips.length; i++) {
                if (ips[i] == ip) {
                    pos = i;
                    return pos;
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }

    public static boolean octeto1Ok(String mascara) {
        boolean octetoOk = false;
        String[] aux = mascara.split("\\.");
        String[] valores = {"128", "192", "224", "240", "248", "252", "254", "255"};
        for (int i = 0; i < 1; i++) {
            for (String valore : valores) {
                if (aux[0].contains(valore)) {
                    octetoOk = true;
                }
            }
        }

        if (octetoOk && Objects.equals(aux[1], "0") && Objects.equals(aux[2], "0")
                && Objects.equals(aux[3], "0")) {
            octetoOk = true;

        }
        return octetoOk;
    }

    public static boolean octeto2Ok(String mascara) {
        boolean octetoOk = false;
        String[] aux = mascara.split("\\.");
        String[] valores = {"128", "192", "224", "240", "248", "252", "254", "255"};
        for (int i = 0; i < 1; i++) {
            for (String valore : valores) {
                if (aux[1].contains(valore)) {
                    octetoOk = true;
                }
            }
        }
        if (Objects.equals(aux[0], "255") && octetoOk
                && Objects.equals(aux[2], "0") && Objects.equals(aux[3], "0")) {
            octetoOk = true;

        }
        return octetoOk;
    }

    public static boolean octeto3Ok(String mascara) {
        boolean octetoOk = false;
        String[] aux = mascara.split("\\.");
        String[] valores = {"128", "192", "224", "240", "248", "252", "254", "255"};
        for (int i = 0; i < 1; i++) {
            for (String valore : valores) {
                if (aux[2].contains(valore)) {
                    octetoOk = true;
                }
            }
        }
        if (Objects.equals(aux[0], "255") && Objects.equals(aux[1], "255")
                && octetoOk && Objects.equals(aux[3], "0")) {
            octetoOk = true;

        }
        return octetoOk;
    }

    public static boolean octeto4Ok(String mascara) {
        boolean octetoOk = false;
        String[] aux = mascara.split("\\.");
        String[] valores = {"128", "192", "224", "240", "248", "252", "254", "255"};
        for (int i = 0; i < 1; i++) {
            for (String valore : valores) {
                if (aux[3].contains(valore)) {
                    octetoOk = true;
                }
            }
        }
        if (Objects.equals(aux[0], "255") && Objects.equals(aux[1], "255")
                && Objects.equals(aux[3], "255") && octetoOk) {
            octetoOk = true;

        }
        return octetoOk;
    }

    static boolean validarMac(String mac) {

        boolean macOk = false;
        mac = mac.toUpperCase();
        String formato1 = "[0-9A-F]{12}";
        String formato2 = "([0-9A-F]{2}\\:){5}[0-9A-F]{2}";
        String formato3 = "([0-9A-F]{2}\\-){5}[0-9A-F]{2}";
        String formato4 = "([0-9A-F]{4}\\.){2}[0-9A-F]{4}";

        if (mac.matches(formato1) || mac.matches(formato2) || mac.matches(formato3) || mac.matches(formato4)) {
            macOk = true;
        }

        return macOk;
    }

    static boolean mismaRed(String ipOtroHost) {
        boolean mismaRed = false;

        if (validarIP(ipOtroHost)) {
          
            
        }
        return mismaRed;
    }

}
