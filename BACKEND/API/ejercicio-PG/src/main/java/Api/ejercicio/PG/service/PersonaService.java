package Api.ejercicio.PG.service;

import java.time.LocalDate;
import java.time.Period;

//@Service
public class PersonaService {
    public String getEdad (int anio, int mes, int dia) {
        String edad = "";
        if (anio > 0 && mes >= 1 && mes <= 12 && dia >= 1 && dia <= 31) {
            LocalDate.of(anio, mes, dia);
            LocalDate.now();
            LocalDate currentDate = LocalDate.now();
            LocalDate birthDate = LocalDate.of(anio, mes, dia);
            edad = String.valueOf(Period.between(birthDate, currentDate).getYears());
//       edad = String.valueOf((LocalDate.now().minusYears(anio)).getYear());


        } else {

               edad="No se pudo calcular la edad";
        }
        return edad;
    }
}