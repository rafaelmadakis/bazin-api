# Bazin API (Spring Boot) - ZIP Release

Conteúdo: API REST que calcula o preço-teto pelo método Bazin a partir dos 5 últimos dividendos.
Inclui autenticação via JWT (login → token → rotas protegidas), DTOs, exceções customizadas e instruções.

## Rodando localmente

1. Ajuste `src/main/resources/application.properties` (jwt.secret, porta, etc).
2. Build:
   mvn clean package
3. Run:
   mvn spring-boot:run
   ou execute a classe `com.example.bazin.BazinApiApplication` pelo IDE.

## Endpoints
- POST /api/auth/login  → Body: { "username":"user","password":"password" } returns { "token": "..." }
- POST /api/v1/bazin/price-teto  → Protected: Authorization: Bearer <token>

## Exemplo de body (price-teto)
{
"dividends": [1.25, 1.40, 1.55, 1.70, 1.85],
"desiredYieldPercent": 6
}
