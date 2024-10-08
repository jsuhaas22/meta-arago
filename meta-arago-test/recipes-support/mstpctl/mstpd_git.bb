SUMMARY = "Multiple Spanning Tree Protocol Daemon"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4325afd396febcb659c36b49533135d4 \
                    file://debian/copyright;md5=332234a99007d25da40f41ee96aa388f"

SRC_URI = "git://github.com/mstpd/mstpd.git;protocol=https;branch=master \
           file://0001-gcc8-strncpy-werror-workaround.patch"

PV = "0.0.7+git"
SRCREV = "e4f0ba5a48649a3253f8b353c87c965e12aafc50"

S = "${WORKDIR}/git"

DEPENDS = "python3"
RDEPENDS:${PN} = "python3-core"

inherit autotools

EXTRA_OECONF = "--sbindir=${base_sbindir}"

do_configure:prepend() {
	 sed -i -e "s,#!.*python.*,#!${USRBINPATH}/env python3," ${S}/utils/ifquery
}
