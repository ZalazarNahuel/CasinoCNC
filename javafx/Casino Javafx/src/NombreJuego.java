enum NombreJuego{
    UNO("UNO"), DOS("DOS"), TRES("TRES"), CUATRO("CUATRO"), CINCO("CINCO"), SEIS("SEIS"), ESCALERA("ESCALERA"), FULL("FULL"), POKER("POKER"), GENERALA("GENERALA");
    String nombre;
    private NombreJuego(String nombrex){
        nombre = nombrex;
    }

    public String getNombre() {
        return nombre;
    }
}