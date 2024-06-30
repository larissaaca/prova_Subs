# Weather Data Collection Project

Este projeto Java realiza a coleta de dados climáticos de diversas cidades utilizando a API do Open-Meteo. Ele demonstra o uso de threads para melhorar o desempenho da coleta de dados.

## Funcionalidades

- **Coleta de Dados**: Utiliza a API do Open-Meteo para obter dados diários de temperatura (mínima, máxima e média) para várias cidades.
- **Processamento Assíncrono**: Demonstra diferentes abordagens usando threads para processar múltiplas requisições em paralelo.

## Componentes

### Classes Principais:

- **Main.java**: Contém o método `main` para iniciar o programa, configurar experimentos e mostrar resultados.
- **WeatherDataCollector.java**: Gerencia a coleta e processamento de dados, utilizando threads para melhorar o desempenho.
- **WeatherApiService.java**: Realiza requisições HTTP para a API do Open-Meteo e retorna os dados de temperatura.
- **CityCoordinates.java**: Mapeia coordenadas geográficas para cidades específicas.
- **CityWeatherData.java**: Armazena os dados climáticos processados para cada cidade.
- **WeatherData.java**: Representa os dados de temperatura (mínima, máxima, média) para um único dia.

## Uso

1. **Configuração**: Certifique-se de ter o Java instalado e configurado corretamente.
2. **Compilação**: Compile o projeto usando sua IDE ou via linha de comando.
3. **Execução**: Execute a classe `Main.java` para iniciar o programa e visualizar os resultados dos experimentos.

## Experimentos

O programa realiza experimentos para comparar o tempo de execução com diferentes números de threads e cidades por thread.

## Contribuições

Contribuições são bem-vindas! Para reportar bugs, sugerir melhorias ou enviar novos recursos, por favor abra uma *issue* ou envie um *pull request*.

