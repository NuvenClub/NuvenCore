[![](https://i.imgur.com/7R0JLtg.png)](https://return-3.gitbook.io/ncore/)
# Sobre o nCore

nCore é um sistema privado e completo utilizado como ponto central para a organização mais eficientes e simples dos servidores, o cores é responsável pelos ligação do Minigame, Lobbies, banco de dados, API's e integrações.

## Características Principais
<sub> **Language.yml | Customizável**</sub>

No arquivo Language.yml é possivel editar: 
- Menus, hologramas, mensagens e descrições de comandos.
- Titles e subtitles e etc.

<sub> **Compatibilidades | Forks, API's**</sub>

- ✔ **DecentHolograms**: Utilizar para a API do holograma.
- ✔ **Discord**: Vincular com o bot do discord e etc.
- ✔ **Vault**: Coins vinculados com o vault.

<sub> **Sistemas | Otimizados e Compartilhados**</sub>
- **NÍVEIS**: Sistema de níveis, para cada minigame, como os lobbies tem o servidor compartilhado, o nível no chat deverá aparecer por qual local o jogador mandou mensagem no chat. Ou seja, se o jogador estiver no Lobby, não aparecerá o nível atrás do nick no chat. <--- * Não possui Nível para toda a Network, como no hypixel.
- **CASH**: Economia do servidor utilizada para comprar, produtos da loja de cash, kits e habilidades nos minigames, upgrade de vip.
- **Lobby**: Todos os lobbies estarão no mesmo servidor, ou seja, o Lobby #1 será o mesmo servidor em todos os minigames, terão o Tab e chat compartilhados. Os Lobbies seguintes como Lobby #2 ou Lobby #3 serão outros servidores.
- **NPC**(jogar): Seguindo a lógica de lobbies acima, cada mapa terá seu NPC de jogar para entrar nas partidas de cada minigame.
- **Proteção de NPC**: ao jogador chegar a =< 1 bloco de distância o jogador deve ficar invisível. caso o jogador entre no bloco que o npc está o jogador será jogado para outra direção.
- **Custom Join Items**
- **SISTEMAS DE ARENA BUNGEE-MODE**: Um novo modo de bungee escalável. Ele pode hospedar várias arenas na mesma instância do servidor, clonar e iniciar novas arenas quando necessário, para que outros jogadores possam participar. O servidor pode ser reiniciado automaticamente após uma certa quantidade de jogos jogados. Isso exigirá a instalação do nSkywarsProxy em seus servidores de lobby para que os jogadores possam participar. E, é claro, você pode executar quantos servidores quiser no modo bungee.
- **Redirecionamento**: Se um servidor for reiniciado ou cair por qualquer motivo, os jogadores devem ser redirecionados para um servidor de Lobby disponível.

<sub> **Sistemas | Chat e Permissões**</sub>
- **Chat**: O chat deverá ter as seguinte configurações para um bom funcionamento, todos os lobbys, devem ver o mesmo chat, exceto os que estão em outros servidores, a menos que seja usado um comando de /aviso ou /g para avisos;
- **Formato**: ```{medalha} {Simbolo_de_liga/nível do minigame} {player}: {menssage} ```

![](https://2103586585-files.gitbook.io/~/files/v0/b/gitbook-x-prod.appspot.com/o/spaces%2FxvnUfc48oiVZwoEeWcLW%2Fuploads%2FnkOf3LGdUHHXGUwSallZ%2Fimage.png?alt=media&token=13c8d0b8-e170-4419-be32-f7854029e55c)


-  ```ncore.chat.bypass.staff```: Permissão destinada a membros da equipe para que possam ignorar as preferências do chat de todos os jogadores.
-  ```ncore.chat.bypass.delay```: Permissão para dar bypass no delay do chat.
-  ```ncore.chat.bypass.link```: Permissão para enviar links no chat.
-  ```ncore.chat.bypass.badwords```: Permissão para dar visualizar mensagens com conteúdos bloqueados/ilícitos.
-  ```ncore.chat.colors```: Utilizar cores nos servidores.
-  ```ncore.chat.destaque```: Mensagens destacadas, ou seja na coloração padrão, branca em vez de cinza.
-  ```ncore.chat.clearchat```: Apagar completamente os chats, nos lobbies, deve ter um variação para apagar o chat em todos os lobbies. (/ncore clearchat , /ncore clearchat all)
-  ```ncore.chat.spy```: O jogador com permissão poderá ver as mensagens enviadas por /tell. Comando necessário para impedir negociações ou pegar provas de conteúdos ilícitos mandado em mensagens privadas. (/ncore chatspy all & /ncore chatspy <player>)
-  ```ncore.chat.aviso```: Permissão para usar o chat, onde a mensagem é exibida em todos os servidores. (/aviso <mensagem>).

<sub> **Sistemas | Preferências do Chat**</sub>
-  ```/ncore chat metion```: Comando para ativar ou desativar menções no chat. Quando o jogador for notificado, aparece uma mensagem na Actionbar, avisando a menção e toca um som de XP_ORB;
- ```/ncore chat tell```: Comando para ativar ou desativar mensagens privadas;
- ```/ncore chat geral```: Comando para ativar ou desativar mensagens do jogadores no servidor.
- ```/ncore chat filter (all/block)```: Comando para ativar ou desativar a visualização de palavras filtradas pelo servidor.
- ```/ncore chat ignore (player)```: Comando para ignorar mensagens privadas de um jogador.

## Características em Desenvolvimento
<sub> **Em-Desenvolvimento | Funções principais abaixo**</sub>

Com o propósito de aprimorar o fluxo atual para os futuros minigames, optamos por reformular a Core, visando uma maior facilidade no desenvolvimento e prevenção de possíveis falhas ou problemas.

Os últimos downloads, wiki e outros links úteis podem ser encontrados na página inicial do projeto em [docs.nuven.club](https://return-3.gitbook.io/ncore/)

- [ ] Banco de Dados com Hikari
- [ ] Perfil de Jogadores
- [ ] Comandos
- [ ] Sistema de Níveis
- [ ] Sistema de NPC (Proteção)
- [ ] Econômia de Cash(Global) & Coins
- [ ] Bungeecord (Lobby -> minigame)
- [ ] Sistemas de arena Bungee-mode
- [ ] Redirecionamento de Servidores
- [ ] Chat (Hover's, chat privado & administração de chat)
- [ ] [Preferências](https://return-3.gitbook.io/ncore/preferencias)
- [ ] [BOT - Discord](https://return-3.gitbook.io/ncore/outros/bot-do-discord) (Vinculação com o Servidor)
- [ ] Compatibilidade com DecentHolograms, Vault.
- [ ] [Sistema de Lobbies](https://return-3.gitbook.io/ncore/outros/lobby)

## Requisitos
Este plugin funciona em **Spigot** e **BungeeCord/Variações**. Oficialmente, é suporta
servidores Spigot, Paper & Waterfall. É necessário usar o **Java 8** Spigot 1.8.8_R3.

### Progresso com Traduções
Este arquivo será disponibilizado para que outros usuários possam contribuir com o Projeto durante sua tradução.
- [Traduções](https://crowdin.com/project/ncore/translations)

# Libraries externas
- [bStats](https://bstats.org/getting-started/include-metrics)
- [HikariCP](https://mvnrepository.com/artifact/com.zaxxer/HikariCP)
- [SLF4J](http://www.slf4j.org/)
