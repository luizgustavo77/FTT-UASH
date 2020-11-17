$(document).ready(function () {
    usuarios().pesquisar();
});

var usuarios = function () {

    var controles = function () {
        return {
            Tabela: "#tabela",
            Email: "#inputEmail",
            CPF: "#inputCPF",
            CEP: "#inputCEP",
            Senha: "#inputPassword",
            ConfirmarSenha: "#inputPasswordValid",
            modalBtn: "#modalBtn"
        };
    }

    var getFiltros = function () {
        var dto = {
            Email: $(controles().Email).val(),
            CPF: $(controles().CPF).val(),
            CEP: $(controles().CEP).val(),
            Senha: $(controles().Senha).val(),
            ConfirmarSenha: $(controles().ConfirmarSenha).val()
        };

        return dto;
    }

    var pesquisar = function () {
        mostrarLoading();

        // $.ajax({
        //     type: "POST",
        //     url: base_path + "Usuarios/ListaTabela",
        //     data: {
        //         'dto': getFiltros()
        //     },
        //     cache: false,
        // }).done(function (data) {
        //     if (data) {
        //         montarTabela(data);
        //     }
        //     else {
        //         removerLoading();
        //     }
        // }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
        //     alert('Problemas ao carregar a pesquisa de usuarios. ' + errorThrown);
        // });

        montarTabela();
    }

    var montaModal = function (linhaDataTable) {
        mostrarLoading();
        var dto = ExtrairObjeto(linhaDataTable, controles().Tabela);

        $(controles().modalBtn).click();

        if (dto) {
            $(controles().Email).val(dto.email);
            // ...

            removerLoading();
        }
        else {
            alert("Problema ao carregar");
            removerLoading();
        }
    }

    var deletar = function () {
        mostrarLoading();
        var dto = ExtrairObjeto(linhaDataTable, controles().Tabela);

        // $.ajax({
        //     type: "POST",
        //     url: base_path + "Usuarios/deletar",
        //     data: {
        //         'id': dto.id
        //     },
        //     cache: false,
        // }).done(function (data) {
        //     if (data) {
        //         alert("Apagado");
        //         removerLoading();
        //     }
        //     else {
        //         removerLoading();
        //     }
        // }).fail(function (XMLHttpRequest, textStatus, errorThrown) {
        //     alert('Problemas ao deletar o usuario. ' + errorThrown);
        // });
    }

    var montarTabela = function (lista) {
        $(controles().Tabela).DataTable({
            dom: 'Bfrtip', buttons: ['excelHtml5'],
            data: lista,
            destroy: true,
            filter: false,
            info: false,
            paginate: true,
            paginationType: 'full_numbers',
            lengthChange: false,
            iDisplayLength: 10,
            language: {
                processing: 'Processando...',
                zeroRecords: 'Nenhum registro encontrado.',
                paginate: {
                    first: '&laquo;',
                    previous: '<',
                    next: '>',
                    last: '&raquo;'
                }
            },
            order: [[0, 'asc']],
            columns: [
                {
                    data: "cpf",
                    title: 'Usuario CPF',
                    sortable: true,
                    render: function (data) {
                        if (data)
                            return Nome;
                        else
                            return "Sem CPF";
                    }
                },
                {
                    data: 'Perfil',
                    title: 'Perfil',
                    sortable: true,
                    render: function (data) {
                        if (data) {
                            return data;
                        }
                        else
                            return "Sem perfil";
                    }
                },
                {
                    data: 'cep',
                    title: 'CEP',
                    sortable: true,
                    render: function (data) {
                        if (data)
                            return data;
                        else
                            return "Sem CEP";
                    }
                },
                {
                    data: null,
                    title: 'A&ccedil;&otilde;es',
                    'class': 'centro',
                    sortable: false,
                    render: function (data) {
                        var html = "";
                        html = "<a title='Alterar' data-toggle='tooltip' onclick='usuarios.montaModal(this); return false;' data-original-title='Alterar' style='padding:3px;'>"
                            + "<span class='glyphicon glyphicon-pencil'></span>"
                            + "</a>";
                        html += "<a title='Excluir' data-toggle='tooltip' onclick='usuarios.deletar(this);' data-original-title='Excluir' style='padding:3px;'>"
                            + "<span class='glyphicon glyphicon-remove'></span>"
                            + "</a>";

                        return html;
                    }
                }
            ]
        })

        removerLoading();
    }

    return {
        pesquisar: pesquisar,
        montaModal: montaModal,
        deletar: deletar

    }
}