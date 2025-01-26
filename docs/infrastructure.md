# Infrastructure

- Code hosted on Github
- CI/CD via Github Actions
- App hosted using [dokku](https://dokku.com/docs/) on a [Hostinger VPS](https://hpanel.hostinger.com/)
- Database fully managed by [Tembo](https://cloud.tembo.io/)

## Setup Database Connection Information on Dokku

Set the database connection environment variable with values the database host provider.

```shell
dokku config:set workout PG_HOST=""
dokku config:set workout PG_PORT=""
dokku config:set workout PG_DB_NAME=""
dokku config:set workout PG_USERNAME=""
dokku config:set workout PG_PASSWORD=""
```

## Dokku Configuration

Basic reference: [Hostinger guide](https://support.hostinger.com/en/articles/10100263-how-to-use-the-dokku-vps-template)

On VPS
- Configure global domain for deployments:

```shell
dokku domains:set-global ohdyno.app
```

- Enable SSL ([Dokku Doc](https://dokku.com/docs/deployment/application-deployment/?h=letsencrypt#setting-up-ssl))

```shell
dokku plugin:install https://github.com/dokku/dokku-letsencrypt.git
dokku letsencrypt:set --global email xing@ohdyno.app
dokku letsencrypt:cron-job --add
dokku letsencrypt:enable workout # after deploying
```

On Local Machine:
- Add SSH public key to Dokku to support deploying from Github

```shell
cat ~/.ssh/<key>.pub | ssh root@ohdyno.app dokku ssh-keys:add GH
```

