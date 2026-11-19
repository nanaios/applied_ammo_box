# Changelog

<!--
「## <バージョン>」の節が、そのバージョンの更新履歴として Modrinth・CurseForge・GitHub Releases に
公開されます（.github/workflows/publish.yml）。見出しは gradle.properties の mod_version と同じ文字列に
してください。リリースするときは「## Unreleased」をそのバージョンに書き換えます。
1.20.1-1.2.3 までの履歴は Modrinth・CurseForge の各バージョンのページにあります。
-->

## 1.20.1-1.2.5

- Fixed ammo disappearing when it is returned to a Wireless Ammo Box (unloading a gun in TACZ 1.2.0+, or filling the box from an Ammo Storage block). Returned ammo is now inserted into the linked ME network; anything the network cannot accept is dropped at the player's position.
- Fixed the Wireless Ammo Box removing ammo from the ME network when its power ran out.
- Fixed a possible crash on clients connected to a dedicated server when the Wireless Ammo Box ran out of power.


## 1.20.1-1.2.4

- Fixed a crash with AE Infinity Booster 2.x ([#13](https://github.com/nanaios/applied_ammo_box/issues/13))
