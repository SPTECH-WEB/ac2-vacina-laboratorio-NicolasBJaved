package sptech.school;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Vacina vacina1 = new Vacina("123", "sla1", "tipo1", 20.2, 91.3,
                LocalDate.now().minusDays(4));

        Vacina vacina2 = new Vacina("1234", "sla2", "tipo1", 22.2, 91.3,
                LocalDate.now().minusDays(3));

        List<Vacina> vacinas = new ArrayList<>();
        vacinas.add(vacina1);
        Laboratorio laboratorio1 = new Laboratorio(vacinas);

        System.out.println(laboratorio1.vacinas.get(0).nome);

        laboratorio1.adicionarVacina(vacina2);

        System.out.println(laboratorio1.vacinas.get(1).nome);

        System.out.println(laboratorio1.buscarVacinaPorCodigo("123").nome);

        //laboratorio1.removerVacinaPorCodigo("123");
        //LINHA A BAIXO TEM QUE DAR ERRO APÓS A EXECUÇÃO DA EXCLUSÃO
        //System.out.println(laboratorio1.buscarVacinaPorCodigo("123").nome);

        System.out.println("EFICÁCIA");
        System.out.println(laboratorio1.buscarVacinaComMelhorEficacia().nome);

        LocalDate dataInicio = LocalDate.now().minusDays(5);
        LocalDate dataFim = LocalDate.now().plusDays(5);

        System.out.println("----------------------------------------------------------------------");
        List<Vacina> vacinasPorPeriodo = laboratorio1.buscarVacinaPorPeriodo(dataInicio,dataFim);

        for(Vacina v : vacinasPorPeriodo){
            System.out.println(v.nome);
        }

        System.out.println("Você pode usar essa classe para testar o seu código se precisar.");
    }
}