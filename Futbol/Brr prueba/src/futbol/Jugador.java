package futbol;

// declaracion de la clase


public class Jugador {

  // Las propiedades
  String name;
  boolean gender;

  // Los Metodos
  // el metodo Constructor
  public Jugador() {

  }

  void setName(String name) {
    this.name = name;
  }

  void setGender(boolean gender) {
    this.gender = gender;
  }

  void setGender(String gender) {
    if (gender == "hombre") {
      this.gender = false;
    }
  }

  String getGender() {
    if (this.gender==false) {
      return "hombre";
    } else {
      return "mujer";
    }
  }

}
