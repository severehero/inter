esse é um site para auxilo para clinicas dentarias 

que teve adicionado 36 classes sobre usuarios, clientes, dentistas, secretarios, ficha, plano, pagamento e agenda tudo com entitie, repository, services e controller 


1. Entidades (Entities)
Usuario (User): A tabela usuario foi criada para armazenar informações de autenticação e dados dos usuários do sistema. A classe Usuario foi atualizada para refletir os campos id, nome, email e senha.

Cliente (Client): A tabela cliente foi criada com a relação de id e cpf (único). A chave estrangeira idCli foi configurada para referenciar o usuário associado. A classe Client foi atualizada para incluir campos como cpf e id, com a chave estrangeira para o relacionamento com a tabela usuario.

Dentista (Dentist): A tabela dentista contém as colunas id, cro e especializacao. A classe Dentist foi mapeada com esses campos e a chave primária id. Foi configurado o relacionamento com a tabela usuario via a chave estrangeira id.

Plano (Plan): A tabela plano armazena informações sobre os planos de saúde com os campos status, descricao, valormensal e valoranual. A classe Plan foi atualizada para incluir esses campos e o relacionamento com o processo de cadastro do plano, refletindo as operações via o procedimento sp_CadastrarPlano.

Ficha (HealthRecord): A tabela ficha foi ajustada para refletir dados relacionados à saúde do cliente, com campos como DataNasc, EstadoCivil, Endereco, Alergia, entre outros. A chave estrangeira idCli foi configurada para associar cada ficha ao cliente correspondente. A classe HealthRecord foi atualizada para mapear corretamente os dados e as relações.

Pagamento (Payment): A tabela pagamento registra os pagamentos realizados pelos clientes, com os campos StatusPay, DataPay, Nome, e QuantValor. A classe Payment foi configurada para refletir esses campos e integrar a lógica de negócios de pagamento.

Agenda (Appointment): A tabela agenda registra os agendamentos, com os campos DataDia e Hora. A classe Appointment foi ajustada para refletir essas informações, facilitando o controle de horários e datas.

2. Procedimentos Armazenados (Stored Procedures)
sp_CadastrarPlano: Este procedimento é utilizado para cadastrar um novo plano de saúde no sistema, inserindo os dados do plano, incluindo status, descricao, valormensal e valoranual, diretamente na tabela plano.

sp_CadastrarUsuario: Este procedimento armazena as informações de um novo usuário, incluindo Nome, Email e Senha.

3. Relacionamentos e Restrições
Chaves Estrangeiras: A tabela cliente contém uma chave estrangeira idCli que referencia a tabela usuario. A tabela dentista também contém uma chave estrangeira id referenciando o usuario. A tabela ficha está relacionada à tabela cliente através da chave idCli.

Restrições de Chave Primária: Cada tabela foi configurada com uma chave primária única, como id em agenda, plano, usuario e dentista, garantindo a unicidade e a consistência dos dados.

Relacionamento entre Tabelas: A tabela cliente possui uma chave estrangeira referenciando usuario, e a tabela ficha está relacionada à tabela cliente através da chave idCli. A tabela dentista possui um relacionamento com a tabela usuario, garantindo que cada dentista esteja associado a um usuário no sistema.

4. Ajustes na Lógica de Negócio
Cadastro de Usuários: A função de cadastro de usuários foi adaptada para utilizar o procedimento armazenado sp_CadastrarUsuario, permitindo o registro de usuários no sistema.

Cadastro de Planos: O cadastro de planos foi otimizado utilizando o procedimento armazenado sp_CadastrarPlano, garantindo o armazenamento eficiente e consistente das informações sobre planos.

Pagamentos: A classe PaymentService foi aprimorada para refletir os campos de pagamento, incluindo o cálculo de valores e verificação do status de pagamento, utilizando a tabela pagamento.

Agendamentos: A lógica de agendamento de consultas foi expandida, com o sistema permitindo a criação e consulta de agendamentos, agora refletindo os dados armazenados na tabela agenda.
