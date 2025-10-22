package sptech.school;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.VacinaInvalidaException;
import school.sptech.exception.VacinaNaoEncontradaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Laboratorio {
    String nome;
    List<Vacina> vacinas;

    public Laboratorio() {
    }

    public Laboratorio(List listaVacinas) {
        this.vacinas = listaVacinas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarVacina(Vacina vacina) throws school.sptech.exception.VacinaInvalidaException {
        if(vacina == null){
            throw new VacinaInvalidaException("Vacina inválida");
        }else if(vacina.codigo == null || vacina.codigo.isEmpty()){
            throw new VacinaInvalidaException("Vacina inválida");
        }else if(vacina.nome == null || vacina.nome.isEmpty()){
            throw new VacinaInvalidaException("Vacina inválida");
        }else if(vacina.tipo == null || vacina.tipo.isEmpty()){
            throw new VacinaInvalidaException("Vacina inválida");
        }else if(vacina.preco == null || vacina.preco <= 0){
            throw new VacinaInvalidaException("Vacina inválida");
        }else if(vacina.eficacia < 0 || vacina.eficacia > 100){
            throw new VacinaInvalidaException("Vacina inválida");
        }else if(vacina.dataLancamento == null || vacina.dataLancamento.isAfter(LocalDate.now())){
            throw new VacinaInvalidaException("Vacina inválida");
        }else{
            this.vacinas.add(vacina);
        }
    }

    public Vacina buscarVacinaPorCodigo(String codigo) throws school.sptech.exception.ArgumentoInvalidoException,
            school.sptech.exception.VacinaNaoEncontradaException {
        if(codigo == null || codigo.isBlank() || codigo.isEmpty() ){
            throw new ArgumentoInvalidoException("Código inválido");
        }

        for(Vacina v : this.vacinas){
            if(v.codigo.equals(codigo)){
                return v;
            }
        }

        throw new VacinaNaoEncontradaException("Vacina não encontrada");
    }

    public void removerVacinaPorCodigo(String codigo){
        if(codigo == null || codigo.isBlank() || codigo.isEmpty() ){
            throw new ArgumentoInvalidoException("Código inválido");
        }

        for(Vacina v : this.vacinas){
            if(v.codigo.equals(codigo)){
                this.vacinas.remove(v);
                return;
            }
        }

        throw new VacinaNaoEncontradaException("Vacina não encontrada");
    }

    public Vacina buscarVacinaComMelhorEficacia() throws VacinaNaoEncontradaException{
        if(this.vacinas.isEmpty()){
            throw new VacinaNaoEncontradaException("Vacina não encontrada");
        }

        Vacina vacinaMelhorAtual = null;
        for(Vacina v : this.vacinas){
            if(vacinaMelhorAtual == null){
                vacinaMelhorAtual = v;
            }else if(v.eficacia > vacinaMelhorAtual.eficacia){
                vacinaMelhorAtual = v;
            }else if(v.eficacia.equals(vacinaMelhorAtual.eficacia)){
                if(v.dataLancamento.isAfter(vacinaMelhorAtual.dataLancamento)){
                    vacinaMelhorAtual = v;
                }
            }
        }

        return vacinaMelhorAtual;
    }

    public List<Vacina> buscarVacinaPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws ArgumentoInvalidoException{
        if(dataInicio == null || dataFim == null || dataInicio.isAfter(dataFim)){
            throw new ArgumentoInvalidoException("Data inválida");
        }

        List<Vacina> listaVacinasPeriodo = new ArrayList<>();

        for(Vacina v : this.vacinas){
            if(v.dataLancamento.isAfter(dataInicio) && v.dataLancamento.isBefore(dataFim)){
                listaVacinasPeriodo.add(v);
            }
        }

        return listaVacinasPeriodo;
    }

    public List<Vacina> getVacinas() {
        return vacinas;
    }
}
