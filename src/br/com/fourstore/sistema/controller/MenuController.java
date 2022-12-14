package br.com.fourstore.sistema.controller;

import br.com.fourstore.sistema.utils.Validations;

public class MenuController {

    Validations validations;

    public MenuController() {
        this.validations=new Validations();

    }
    public int validationRegexMenu(String entrada, String verificMenu) {
        return validations.menuValidation(entrada, verificMenu);

    }

    public boolean validarCpf(String cpf) {
        return validations.cpfValidation(cpf);

    }

    public boolean validateCpfRegex(String cpf) {
        return validations.validateCpfRegex(cpf);
    }

    public boolean validationCard(String acceptedCards) {
        return validations.validateCard(acceptedCards);
    }


}

