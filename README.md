# My Money Academy 

## Sobre o projeto

O foco do nosso projeto é unir oportunidade no mercado financeiro com educação financeira. Existe um grande problema no Brasil: nós temos recordes de endivamento da população brasileira, alcançando  76% de familias endividadas  em 2022. Além desse dado que já assusta, podemos apresentar mais dois: 58% dos brasileiros não se dedicam as próprias finanças e apenas 3% da população investe.   

Para alcançarmos o empoderamento da população, não basta aumentar o número de investidores na bolsa, precisamos lidar com o alto endividamento da população através de educação financeira para que cada vez mais pessoas possam ter o privilégio de investir, com tantas empresas buscando isso, que tal conectarmos endividados com educação financeira para um Brasil menos endividado e mais emporado?

### O projeto contém 3 entidades

/investment - endpoint com os dados de investimentos <br />
/debts  - endpoint com os dados de dívidas<br />
/blog  - endpoint com os dados de artigos e publicações sobre educação financeiro


## Documentação postman:
​​https://documenter.getpostman.com/view/6317832/UyrAGcw1

## Testar a API online
Foi feito o deploy da api na AWS no endereço:

Investimento <br />
http://ec2-3-15-11-121.us-east-2.compute.amazonaws.com:8080/investments

Dividas <br />
http://ec2-3-15-11-121.us-east-2.compute.amazonaws.com:8080/debts

Investimento <br />
http://ec2-3-15-11-121.us-east-2.compute.amazonaws.com:8080/blogs


## Como iniciar o banco de dados localmente:
O banco de dados foi criado com docker-compose. No repositório existe o arquivo docker-compose.yaml com a configuração do banco, para fazer funcionar basta instalar o docker e executar o comando "docker-compose up fiap_database" na mesma pasta que está o arquivo yaml.




## Desenvolvedores

- Leandro Sena
- Iago Tavares