# Infrastructure

- Code hosted on Github
- CI/CD via Github Actions
- App hosted using dokku on a Hostinger VPS
- Database fully managed by Xata

## Connect Xata Connection Information on Dokku

Set the database connection url environment variable with value from Xata

```shell
dokku config:set workout DATABASE_URL_POSTGRES=<xata connection url>
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

