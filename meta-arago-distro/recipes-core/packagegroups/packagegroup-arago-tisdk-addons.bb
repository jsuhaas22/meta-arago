DESCRIPTION = "Task to install additional utilities/demos for SDKs"
LICENSE = "MIT"
PR = "r80"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES =+ "${PN}-extra"

UTILS = " \
    gdbserver \
    oprofile \
    ptpd \
    strongswan \
    kexec \
    kdump \
    open62541-examples \
    open62541-tests \
    sudo \
    watchdog \
"

UTILS_UBOOT_FW = "libubootenv-bin"

UTILS_DSP = " \
    ${@bb.utils.contains('MACHINE_FEATURES', 'dsp', 'gdbc6x dsptop', '', d)} \
"

#                       pru-swuart-fw 
UTILS:append:ti33x = " mmc-utils \
                       switch-config \
                       pru-icss \
"

UTILS:append:ti43x = " mmc-utils \
                       switch-config \
                       libdrm-omap \
                       pru-icss \
"

UTILS:append:omap-a15 = " mmc-utils \
                          switch-config \
                          libdrm-omap \
                          stream-openmp \
                          pru-icss \
                          ti-ipc-rtos-fw \
"

UTILS:append:k3 = " mmc-utils \
                    switch-config \
                    irqbalance \
                    ti-rpmsg-char \
                    ti-rpmsg-char-examples \
                    statcol \
"

UTILS:append:k3 = " ti-rtos-firmware"
UTILS:append:am64xx = " pru-icss"
UTILS:append:am62xx = " pru-icss"

ARAGO_JAILHOUSE_ENABLE ?= "0"
UTILS:append:am62xx = " ${@oe.utils.conditional("ARAGO_JAILHOUSE_ENABLE", "1", "jailhouse", "", d)}"

UTILS:append:am65xx = " pru-icss"
UTILS:append:j721e = " pru-icss"

UTILS:append:omapl138 = " ti-ipc-rtos-fw"

UTILS:append:dra7xx = " \
                        ${UTILS_DSP} \
"
# glsdk-example-apps depends on libkms, which has been removed upstream from libdrm:
# https://gitlab.freedesktop.org/mesa/drm/-/commit/2b997bb4bb688be00620887c8646ff24ccb9396b
#                        ${@bb.utils.contains('DISTRO_FEATURES','opengl','glsdk-example-apps','',d)} \
#"

EXTRA_PACKAGES = " \
    protobuf \
    ccief-basic \
    python3-pip \
"

EXTRA_PACKAGES:omapl138 = " \
    protobuf \
"

# acontis-atemsys
#EXTRA_PACKAGES:append:am335x-evm = " pruss-lld-apps"
#EXTRA_PACKAGES:append:am437x-evm = " pruss-lld-apps"
# acontis-atemsys
#EXTRA_PACKAGES:append:am57xx-evm = " pruss-lld-apps"

RDEPENDS:${PN} = "\
    ${UTILS} \
    ${UTILS_UBOOT_FW} \
"

RDEPENDS:${PN}-extra = "\
    ${EXTRA_PACKAGES} \
"
